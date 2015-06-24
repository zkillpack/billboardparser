# McGill Billboard Project Dataset Parser
## Q: Why?
### A: Chords! Hypermeter! PC vectors! For your friendly neighborhood music theorists and -ologists!
The currently available parsers, [the default Haskell-based parser by W. Bas de Haas and John Ashley Burgoyne](http://hackage.haskell.org/package/billboard-parser) and 
[MARL's JAMS parser](https://github.com/marl/jams)
have a focus on audio content retrieval, as is evidenced by the fact that they focus on timestamps 
and give results that do not support queries about musical structure. 

To remedy this, I have decided to adopt a 
whatever-the-hell-is-in-a-musicologist's-brain-first approach rather than a signal-based approach. 
Whatever that actually means...

This is a parser designed for corpus-based musicology and music theory, and as such, spits out
useful information such as:

- Position in meter, hypermeter, and phrase instead of just timestamps
- chord reduction and/or transcription
- pitch class vectors instead of just chord labels
- aggregation by section, beat class, as well as other **musically salient** features


## Using
### Use **SBT** until I bother to actually finish this thing...
``` > sbt run ```