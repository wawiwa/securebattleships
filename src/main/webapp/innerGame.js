        $(document).ready(function() {

            //on document load, set the body background to retina-wood.
            $('body').removeClass().addClass("retina-wood");


            $(".playButtonAction").click(function(e){
                console.log("Play button was pressed");
                console.log(e);

            });



            function hide_target_grid() {
                $("div#gameBox.trGrid").hide();
            }

            function show_target_grid() {
                $("div#gameBox.trGrid").show();
            }

            function hide_ocean_grid() {
                $("div#ocGrid.row-fluid").hide();
            }

            function show_ocean_grid() {
                $("div#ocGrid.row-fluid").show();
            }

            function hide_both_game_grids() {
                hide_target_grid();
                hide_ocean_grid();
            }

            function show_both_game_grids() {
                show_target_grid();
                show_ocean_grid();
            }








            // by default the fire button should be disabled.
            $("#fireMissiles").attr("disabled", true);
            $("#fireMessage").html("");


// Array that will wrap the outgoing Rounds
            var wrapperData = new Array();


            function makeTargetSelections() {

                var outgoingRounds = new Array();
                var count = 0;

                $("table#targetCords td").click(function(e) {

                    console.log("Cell was clicked: ", $(this).attr('id'));

                    var cellClicked = $(this).attr('id');

                    if ($(this).hasClass("hit") || $(this).hasClass("missed") || $(this).hasClass("pending")) {
                        alert("You cannot click the same cell twice.");
                    } else {

                        if (count < 5) {
                            $(this).addClass('pending');
                            var ar = splitt(cellClicked);
                            var tmp = new Shot();
                            tmp.setCoords(ar[0], ar[1]);
                            outgoingRounds.push(tmp);
                            count++;
                        }

                        if (count === 5) {
                        	wrapperData.push({"RoundType": "outgoing", "From": "player1", "To": "player2", "Rounds": outgoingRounds});

                            // 5 cords have been select, enable the fire button
                            $("#fireMissiles").attr("disabled", false);

                            // display the ready to fire Message
                            $("#fireMessage").html("<h3>Ready to Fire</h3>");
                        }

                    }

                });


            }


            var LOGG_ON = true;

            function updateGridWithIncomingShots(jsonData, specific_grid_selector) {
                // This function accepts the post validation json string
                // of an outgoing target grid shots.  It should and can be
                // used for updating both the target and ocean grids.
                
                // Second param is optional. Its used for more specific grid cell updates

                var data = JSON.parse(jsonData);

                if (LOGG_ON) {
                    console.log("The Data", data);
                    console.log(data.length);
                    console.log("Rounds at : ", data[0].Rounds);
                }

                var IR = data[0].Rounds;
                console.log(IR);
                Object.keys(IR).forEach(function(key) {
                    var cord = IR[key].coords
                    var classTOAdd = (IR[key].isHit === false) ? "missed" : "hit";
                    $("#" + cord).addClass(classTOAdd);
                    if (LOGG_ON) { console.log(key, IR[key]); }
                });
                
            }


            $("#fireMissiles").click(function() {

                if (wrapperData[0].Rounds.length === 5) {
                    //5 shots in fact have been select, thus valid
                    var dataInJSON = JSON.stringify(wrapperData);
                    pushTargetGrid([{name:'targetGrid',value:dataInJSON}]);
                    var targetGrid = $("#targetGridForm\\:targetGrid").val();
                    pushTargetGrid([{name:'targetGrid',value:dataInJSON}]);
                    targetGrid = $("#targetGridForm\\:targetGrid").val();
                    console.log("Target Grid: "+targetGrid);
                    updateGridWithIncomingShots(targetGrid);
                    alert(dataInJSON);

                    // This data needs to be sent to the server, check whether or not the select shots were hits
                    // or misses

                    // Then the results from that check are then sent to both the send and the receive to update the
                    // grids

                    // The returned json results should then be passed to the updateGridWIthIncomingShots


                } else {
                    console.log("Error, 5 shots have not been select.  Invalid program state");
                }

            });


            makeTargetSelections();




            ///=========================================

            function l(e) { console.log(e); }

            function JSONBoat(boat) {
                this.name = boat.name;
                this.shipCords = boat.shipCords;
            }

            var gameBoats = new Array();
            function submitShips() {
                //AJAX CALL to the back end will be made from this method.
                // from which the json data will be inserted into the table.


                // console.log("FINAL RESULT: ");
                // console.log(JSON.stringify(gameBoats));
                // alert("Your Gameboard has been submitted to the server");

                console.log("====");
                var first = new JSONBoat(gameBoats[0]);
                var second = new JSONBoat(gameBoats[1]);
                var third = new JSONBoat(gameBoats[2]);
                var conBoatGrid = new Array();
                conBoatGrid.push(first);
                conBoatGrid.push(second);
                conBoatGrid.push(third);
                //locked in version of the ships cords
                console.log(JSON.stringify(conBoatGrid));
                $("#OceanCords").css("background-color", "rgb(42, 166, 173)"); // Makes the Grid appear darker after ships have been positioned
                pushOceanGrid([{name:'oceanGrid',value:JSON.stringify(conBoatGrid)}]); // Sending data to backend
                console.log("Not Using jquery: "+document.getElementById('oceanGridForm:oceanCoords').value); // Getting data from backend
// [{"name":"Carrier","shipCords":["B2","C2","D2","E2","F2"]},{"name":"Destroyer","shipCords":["C6","C7","C8","C9"]},{"name":"Cruiser","shipCords":["F6","G6","H6"]}] 
///////////////////////////////////////////////
//            IMPORTANT FUNCTION
//////////////////////////////////////////////   rgb(104, 158, 161)
            }

            function startShipPlacement(boat) {
                var count = 1;
                $("Table#OceanCords td").click(function(e) {
                    var cellClicked = $(this).attr('id');
                    if ($(this).hasClass("clicked") || $(this).hasClass("disabled")) {
                        alert("You cannot click the same cell twice.");
                    } else {
                        if (count == 1) {
                            boat.shipCords_push(cellClicked);
                            $(this).addClass('clicked');
                            if (boat.shipCords.length == 1) {
                                boat.step1();
                            }
                            count++;
                        }
                        if (count == 2) {
                            if ($(this).hasClass("gr")) {
                                boat.shipCords_push(cellClicked);
                                boat.removePotentialEndsColoring();
                                $(this).addClass('clicked');
                                boat.fillInShip();
                                count++;
                            }
                        }
                        if (count === 3) {
                            //the ship has been built
                            $("#message").html(boat.getName() + " has been built.");
                            $('Table#OceanCords td').off('click');
                            $(this).trigger("SecondShip");
                        }
                    }
                });
            }

            $("Table#OceanCords td").on("SecondShip", function() {
                var boat = new Ship("Destroyer", 4);
                gameBoats.push(boat);
                var count = 1;
                $("Table#OceanCords td").click(function(e) {
                    var cellClicked = $(this).attr('id');
                    if ($(this).hasClass("clicked") || $(this).hasClass("disabled")) {
                        alert("You cannot click the same cell twice.");
                    } else {
                        if (count == 1) {
                            boat.shipCords_push(cellClicked);
                            $(this).addClass('clicked');
                            if (boat.shipCords.length == 1) {
                                boat.step1();
                            }
                            count++;
                        }
                        if (count == 2) {
                            if ($(this).hasClass("gr")) {
                                boat.shipCords_push(cellClicked);
                                boat.removePotentialEndsColoring();
                                $(this).addClass('clicked');
                                boat.fillInShip();
                                count++;
                            }
                        }
                        if (count === 3) {
                            //the ship has been built
                            $("#message").append(", " + boat.getName() + " has been built.");
                            $('Table#OceanCords td').off('click');
                            $(this).trigger("ThirdShip");
                        }
                    }
                });
            });

            $("Table#OceanCords td").on("ThirdShip", function() {
                var boat = new Ship("Cruiser", 3);
                gameBoats.push(boat);
                var count = 1;
                $("Table#OceanCords td").click(function(e) {
                    var cellClicked = $(this).attr('id');
                    if ($(this).hasClass("clicked") || $(this).hasClass("disabled")) {
                        alert("You cannot click the same cell twice.");
                    } else {
                        if (count == 1) {
                            boat.shipCords_push(cellClicked);
                            $(this).addClass('clicked');
                            if (boat.shipCords.length == 1) {
                                boat.step1();
                            }
                            count++;
                        }
                        if (count == 2) {
                            if ($(this).hasClass("gr")) {
                                boat.shipCords_push(cellClicked);
                                boat.removePotentialEndsColoring();
                                $(this).addClass('clicked');
                                boat.fillInShip();
                                count++;
                            }
                        }
                        if (count === 3) {
                            //the ship has been built
                            $("#message").append(", " + boat.getName() + " has been built.");
                            $('Table#OceanCords td').off('click');
                            submitShips();
                        }
                    }
                });
            });


            var carrier = new Ship("Carrier", 5);

            gameBoats.push(carrier);

            startShipPlacement(carrier);



        });