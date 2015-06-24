package billboardparser

import spray.json.DefaultJsonProtocol

/**
 * Created by Dakota on 6/24/2015.
 */
object BBJsonProtocol extends DefaultJsonProtocol {
  implicit val chordFormat = jsonFormat2(Transcription.Chord)
  implicit val barFormat = jsonFormat1(Transcription.Bar)
  implicit val numberedBarFormat = jsonFormat2(Transcription.NumberedBar)
  implicit val phraseFormat = jsonFormat1(Transcription.Phrase)
  // implicit val propsFormat = jsonFormat4(Transcription.Properties)
}
