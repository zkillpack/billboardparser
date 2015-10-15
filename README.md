# McGill Billboard Project Dataset Parser 
## Chords! For music theorists and -ologists!
## Q: Why?
### A: Chords! Hypermeter! PC vectors! For your friendly neighborhood music theorists and -ologists!

The currently available parsers, [the default Haskell-based parser by W. Bas de Haas and John Ashley Burgoyne](http://hackage.haskell.org/package/billboard-parser) and 
[MARL's JAMS parser](https://github.com/marl/jams)
have a focus on audio content retrieval, as is evidenced by the fact that they focus on timestamps 
and give results that do not support queries about musical structure. 

To remedy this, I have decided to adopt a symbolic-music-based approach rather than a signal-based approach. 

This is a parser designed for corpus-based musicology and music theory, and as such, is designed for those who have goals that involve:

- Chord rhythm given by position in meter, hypermeter, and phrase instead of just timestamps
- Chord reduction and/or transcription
- Pitch class vectors instead of just chord labels
- Aggregation by section, beat class, as well as other **musically salient** features

## Using
### Use **SBT** until I bother to actually add a nice interface.
Only spits out chords + beat positions until I actually need more features implemented at the parsing rather than the data processing level.
``` > sbt run ```
