/**
 * Created by Dakota on 6/24/2015.
 */
package billboardparser

import spray.json._ // if you don't supply your own Protocol (see below)
import BBJsonProtocol._

//
//object JsonWriter {
//  def write(t: Any) = {
//    val pkl = t.pickle
//    println(pkl)
//    pkl
//  }
//}

object JsonWriter {
  def write(t: Transcription.Chord) = {
    val pkl = t.toJson
    println(pkl)
    pkl
  }
}