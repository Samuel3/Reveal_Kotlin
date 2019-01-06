import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.apache.commons.io.FileUtils
import org.webjars.WebJarAssetLocator
import java.io.File

// This file contains everything necessary for generating the a presentation.
class Generator {

    fun copyFramework(path: String) {
        val allFiles = WebJarAssetLocator().listAssets("reveal.js")
        allFiles.filter {
            // Filter files from repo which are not used for a pure presentation.
            !it.endsWith(".md") &&
                    !it.endsWith(".scss") &&
                    !it.endsWith("demo.html") &&
                    !it.endsWith("package.json") &&
                    !it.endsWith("bower.json") &&
                    !it.endsWith("Gruntfile.js")
        }.forEach {
            val url = this::class.java.getResource(it)
            val fileName = url.toString().substringAfterLast("webjars/reveal.js/3.5.0")
            FileUtils.copyURLToFile(url, File(path + fileName))
        }
    }

}

fun revealSlides(config: Presentation): String {
    return buildString {
        appendHTML().html {
            head {
                meta(charset = "utf-8")
                meta(name = "viewport", content = "width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no")
                title(config.title)
                link(rel = "stylesheet", href = "css/reveal.css")
                link(rel = "stylesheet", href = "css/theme/${config.theme}.css")
                link(rel = "stylesheet", href = "lib/css/zenburn.css")
                script {
                    +"""
                    var link = document.createElement( 'link' );
                    link.rel = 'stylesheet';
                    link.type = 'text/css';
                    link.href = window.location.search.match( /print-pdf/gi ) ? 'css/print/pdf.css' : 'css/print/paper.css';
                    document.getElementsByTagName( 'head' )[0].appendChild( link );

                """.trimIndent()
                }
            }
            body {
                div {
                    classes = setOf("reveal")
                    div (classes = "slides"){
                        config.slides?.forEach {
                            slide(it)
                        }
                    }
                }
                script {
                    src = "lib/js/head.min.js"
                }
                script {
                    src = "js/reveal.js"
                }
                script {
                    unsafe {

                        +"""
                    Reveal.initialize({
                ${config.configuration ?: ""},
				dependencies: [
					{ src: 'plugin/markdown/marked.js' },
					{ src: 'plugin/markdown/markdown.js' },
					{ src: 'plugin/notes/notes.js', async: true },
					{ src: 'plugin/highlight/highlight.js', async: true, callback: function() { hljs.initHighlightingOnLoad(); } }
				]
			});
                """.trimIndent()
                    }
                }
            }
        }
    }
}

fun DIV.slide(slide: Slide) {
    section {
        if (slide.backgroundColor != null) {
            attributes["data-background-color"] = slide.backgroundColor!!
        }
        if (slide.backgroundImage != null) {
            attributes["data-background-image"] = slide.backgroundImage!!
        }
        if (slide.video != null) {
            attributes["data-background-video"] = slide.video!!
        }
        slide.fragments?.forEach {
            p (classes = "fragment ${it.style}"){
                unsafe {
                    +it.content
                }
            }
        }
        unsafe {
            +slide.content
        }
        aside(classes = "notes") {
            +slide.notes
        }
        slide.slides?.forEach {
            this@slide.slide(it)
        }
    }
}