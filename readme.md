# Reveal Kotlin
[![Download](https://api.bintray.com/packages/samuel3/revealkt-local/revealkt/images/download.svg?version=0.1.0) ](https://bintray.com/samuel3/revealkt-local/revealkt/0.1.0/link)
[![Build Status](https://travis-ci.org/Samuel3/Reveal_Kotlin.svg?branch=master)](https://travis-ci.org/Samuel3/Reveal_Kotlin)
[![codecov](https://codecov.io/gh/Samuel3/Reveal_Kotlin/branch/master/graph/badge.svg)](https://codecov.io/gh/Samuel3/Reveal_Kotlin)

This is a Kotlin binding for [reveal.js](https://revealjs.com/#/) helping you to create easily reveal.js Presentation and generating the boilerplate
code for you

## Include
`Gradle`
```
repositories {
	maven {
		url  "https://dl.bintray.com/samuel3/revealkt-local"
	}
}

dependencies {
    compile 'revealkt:presentation:0.1.0'
}
```

## Usage
```
presentation {
        path = File("c:/temp/") // Path to where the presentation should be stored
        configuration { // Configuration block of reveal js. Here all parameters from Reveal.js can be used.
            slideNumber = false
        }

        slides {
            slide {
                content = "Slide I"
                notes = "Note a"
                backgroundColor = "#ff0000"
                fragments {
                    fragment {
                        style = "grow"
                        content = "Grow"
                    }
                }
                slides { // This allows to add sub slides.
                    slide {
                        content = "Slide II/1"
                    }
                }
            }
        }
    }
```

## Artifact

Currently there is no artifact. But an artifact will be follow soon. I promise ;)

## Feedback

If you have any feedback the write me or file a ticket.

## License

Like the reveal.js this project is licensed as MIT License. See [here](https://github.com/Samuel3/Reveal_Kotlin/blob/master/LICENSE)