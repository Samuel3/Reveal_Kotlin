import com.google.gson.Gson
import java.io.File

// This file contains the dsl configuration for usage.

data class Presentation(
        var path: File? = null,
        var title: String = "Presentation",
        var theme: String = "black",
        var slides: List<Slide>? = null,
        var configuration: Configuration?,
        var customCss: String? = null
)

data class Slide(
        var content: String,
        var notes: String,
        var backgroundColor: String?,
        var backgroundImage: String?,
        var backgroundSize: String = "cover",
        var video: String?,
        var loop: Boolean = false,
        var fragments: List<Fragment>? = null,
        var slides: List<Slide>? = null)

data class Fragment(
        var style: String?,
        var content: String
)

data class Configuration(
        var showControls: Boolean = true,
        var controlsTutorial: Boolean = false,
        var controlsLayout: String = "bottom-right",
        var controlsBackArrow: String = "faded",
        var progress: Boolean = true,
        var slideNumber: Boolean = false,
        var history: Boolean = false,
        var keyboard: Boolean = true,
        var overview: Boolean = true,
        var center: Boolean = true,
        var touch: Boolean = true,
        var loop: Boolean = false,
        var rtl: Boolean = false,
        var shuffle: Boolean = false,
        var fragments: Boolean = true,
        var fragmentInUrl: Boolean = true,
        var embedded: Boolean = false,
        var help: Boolean = false,
        var showNotes: Boolean = false,
        var autoPlayMedia: Boolean? = null,
        var autoSlide: Int = 0,
        var autoSlideStoppable: Boolean = true,
        var autoSlideMethod: String = "Reveal.navigateNext",
        var defaultTiming: Int = 120,
        var mouseWheel: Boolean = false,
        var hideAddressBar: Boolean = true,
        var previewLinks: Boolean = false,
        var transition: String = "convex",
        var transitionSpeed: String = "default",
        var backgroundTransition: String = "fade",
        var viewDistance: Int = 3,
        var parallaxBackgroundImage: String = "",
        var parallaxBackgroundSize: String = "",
        var parallaxBackgroundHorizontal: String? = null,
        var parallaxBackgroundVertical: String? = null,
        var display: String = "block"
) {
    override fun toString(): String {
        return Gson().toJson(this).replace("{", "").replace("}", "")
    }
}

fun presentation(block: PresentationBuilder.() -> Unit): Presentation = PresentationBuilder().apply(block).build()

class PresentationBuilder {
    var path: File? = null
    var title: String = "Presentation"
    var theme: String = "black"
    var customCss: String? = null

    private var configuration: Configuration? = null

    private val slides = mutableListOf<Slide>()

    fun configuration(block: ConfigurationBuilder.() -> Unit) {
        configuration = ConfigurationBuilder().apply(block).build()
    }

    fun slides(block: SLIDES.() -> Unit) {
        slides.addAll(SLIDES().apply(block))
    }

    fun build(): Presentation {
        Generator().copyFramework(path!!.toString())
        val presentation = Presentation(path, title, theme, slides, configuration)
        File(path.toString() + "/index.html").writeText(revealSlides(presentation))
        return Presentation(title = title, configuration = configuration, customCss = customCss)
    }
}

class ConfigurationBuilder {
    var showControls: Boolean = true
    var controlsTutorial: Boolean = false
    var controlsLayout: String = "bottom-right"
    var controlsBackArrow: String = "faded"
    var progress: Boolean = true
    var slideNumber: Boolean = false
    var history: Boolean = false
    var keyboard: Boolean = true
    var overview: Boolean = true
    var center: Boolean = true
    var touch: Boolean = true
    var loop: Boolean = false
    var rtl: Boolean = false
    var shuffle: Boolean = false
    var fragments: Boolean = true
    var fragmentInUrl: Boolean = true
    var embedded: Boolean = false
    var help: Boolean = false
    var showNotes: Boolean = false
    var autoPlayMedia: Boolean? = null
    var autoSlide: Int = 0
    var autoSlideStoppable: Boolean = true
    var autoSlideMethod: String = "Reveal.navigateNext"
    var defaultTiming: Int = 120
    var mouseWheel: Boolean = false
    var hideAddressBar: Boolean = true
    var previewLinks: Boolean = false
    var transition: String = "convex"
    var transitionSpeed: String = "default"
    var backgroundTransition: String = "fade"
    var viewDistance: Int = 3
    var parallaxBackgroundImage: String = ""
    var parallaxBackgroundSize: String = ""
    var parallaxBackgroundHorizontal: String? = null
    var parallaxBackgroundVertical: String? = null
    var display: String = "block"

    fun build(): Configuration = Configuration(showControls, controlsTutorial, controlsLayout, controlsBackArrow, progress, slideNumber, history, keyboard, overview, center, touch, loop, rtl, shuffle, fragments, fragmentInUrl, embedded, help, showNotes, autoPlayMedia, autoSlide, autoSlideStoppable, autoSlideMethod, defaultTiming, mouseWheel, hideAddressBar, previewLinks, transition, transitionSpeed, backgroundTransition, viewDistance, parallaxBackgroundImage, parallaxBackgroundSize, parallaxBackgroundHorizontal, parallaxBackgroundVertical, display)
}

class SlideBuilder {

    var content: String = ""
    var notes: String = ""
    var backgroundColor: String? = null
    var backgroundSize: String = "cover"
    var backgroundImage: String? = null
    var video: String? = null
    var loop: Boolean = false
    private var fragments = mutableListOf<Fragment>()
    private val slides = mutableListOf<Slide>()

    fun slides(block: SLIDES.() -> Unit) {
        slides.addAll(SLIDES().apply(block))
    }

    fun fragments(block: FRAGMENTS.() -> Unit) {
        fragments.addAll(FRAGMENTS().apply(block))
    }

    fun build(): Slide = Slide(content, notes, backgroundColor, backgroundImage, backgroundSize, video, loop, fragments, slides)
}

class FragmentBuilder {

    var style: String? = null
    var content: String = ""

    fun build(): Fragment = Fragment(style, content)
}

class SLIDES : ArrayList<Slide>() {

    fun slide(block: SlideBuilder.() -> Unit) {
        add(SlideBuilder().apply(block).build())
    }
}

class FRAGMENTS : ArrayList<Fragment>() {
    fun fragment(block: FragmentBuilder.() -> Unit) {
        add(FragmentBuilder().apply(block).build())
    }
}