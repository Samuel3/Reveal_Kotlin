import org.junit.Test
import java.io.File

class Test {


@Test
fun test() {
    presentation {
        path = File("./build/tests/")

        configuration {
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
                    fragment {
                        style = "shrink"
                        content = "Shrink"
                    }
                }
            }
            slide {
                content = "Slide II"
                notes = "Note b"
                backgroundColor = "#ff00ff"
                slides {
                    slide {
                        content = "Slide II/1"
                    }
                    slide {
                        content = "Slide II/2"
                        backgroundColor = "#00ff00"
                        video = "https://www.youtube.com/embed/-MXIqqaqmkw"
                        loop = true
                    }
                }
            }
        }
    }
}}