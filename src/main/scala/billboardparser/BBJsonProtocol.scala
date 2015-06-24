package billboardparser

import spray.json.DefaultJsonProtocol

/**
 * Created by Dakota on 6/24/2015.
 */
object BBJsonProtocol extends DefaultJsonProtocol {
  implicit val chordFormat = jsonFormat2(Transcription.Chord)
}
