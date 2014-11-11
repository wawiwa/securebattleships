package ejb.domain;

import java.util.Arrays;

public class OutgoingRounds {
	
	public String RoundType;
	public String From;
	public String To;
	public Round[] Rounds;
	
	/**
	 * @param roundType
	 * @param from
	 * @param to
	 * @param rounds
	 */
	public OutgoingRounds(String roundType, String from, String to, Round[] rounds) {
		super();
		RoundType = roundType;
		From = from;
		To = to;
		Rounds = rounds;
	}

	/**
	 * @return
	 */
	public String getRoundType() {
		return RoundType;
	}
	
	/**
	 * @param roundType
	 */
	public void setRoundType(String roundType) {
		RoundType = roundType;
	}
	
	/**
	 * @return
	 */
	public String getFrom() {
		return From;
	}
	
	/**
	 * @param from
	 */
	public void setFrom(String from) {
		From = from;
	}
	
	/**
	 * @return
	 */
	public String getTo() {
		return To;
	}
	
	/**
	 * @param to
	 */
	public void setTo(String to) {
		To = to;
	}
	
	/**
	 * @return
	 */
	public Round[] getRounds() {
		return Rounds;
	}
	
	/**
	 * @param rounds
	 */
	public void setRounds(Round[] rounds) {
		Rounds = rounds;
	}
	
	@Override
	public String toString() {
		return "OutgoingRounds [RoundType=" + RoundType + ", From=" + From
				+ ", To=" + To + ", Rounds=" + Arrays.toString(Rounds) + "]";
	}
	
}



//[
// {
//   "RoundType": "outgoing",
//   "From": "player1",
//   "To": "player2",
//   "Rounds": [
//     {
//       "isHit": null,
//       "coords": "C5"
//     },
//     {
//       "isHit": null,
//       "coords": "D4"
//     },
//     {
//       "isHit": null,
//       "coords": "E5"
//     },
//     {
//       "isHit": null,
//       "coords": "F5"
//     },
//     {
//       "isHit": null,
//       "coords": "G5"
//     }
//   ]
// }
//]
