# Reveal Kotlin
This is a Kotlin binding for [reveal.js](https://revealjs.com/#/) helping you to create easily reveal.js Presentation and generating the boilerplate
code for you

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

Like the reveal.js this project is licensed as MIT License. See [here](./License)