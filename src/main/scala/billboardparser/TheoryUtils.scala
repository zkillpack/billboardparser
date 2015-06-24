package billboardparser

/**
 * Created by Dakota on 6/24/2015.
 */
object TheoryUtils {

  val beatsPerBar = Map("1/4" -> 1,
    "2/4" -> 2,
    "3/4" -> 3,
    "4/4" -> 4,
    "5/4" -> 5,
    "6/4" -> 6,
    "7/4" -> 7,
    "9/4" -> 9,
    "3/8" -> 1,
    "5/8" -> 2,
    "6/8" -> 2,
    "9/8" -> 3,
    "12/8" -> 4)

  val beatStrengths = Map(
    "4/4"-> List(3,1,2,1),
    "6/4"-> List(3,1,1,2,1,1),
    "9/4"-> List(3,1,1,2,1,1,2,1,1),
    "3/4"-> List(3,1,1),
    "3/8"-> List(3),
    "5/8"-> List(3,1))

  def getStrengths(sig: String, beat: Int) = beatStrengths.get(sig) map (l => l(beat))
}
