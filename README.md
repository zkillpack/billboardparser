# McGill Billboard Project Dataset Parser

_____

## Q: Why?
### Chords! For music theorists and -ologists!
The currently available parsers, [the default Haskell-based parser](http://hackage.haskell.org/package/billboard-parser) and 
[MARL's JAMS parser](https://github.com/marl/jams)
have a focus on audio content retrieval, as is evidenced by the fact that they focus on timestamps 
and give formats that do not support querying based on musical structure.

This is a parser designed for corpus-based musicology and music theory, and as such, spits out
useful information such as:

- Position in meter, hypermeter, and phrase instead of just timestamps
- chord reduction and/or transcription
- pitch class vectors instead of just chord labels
- aggregation by section, beat class, as well as other **musically salient** features

_____

## Using
### Use **SBT** until I bother to actually finish this thing...
``` > sbt run ```