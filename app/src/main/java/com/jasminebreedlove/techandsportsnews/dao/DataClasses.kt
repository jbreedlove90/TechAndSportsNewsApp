package com.jasminebreedlove.techandsportsnews.dao

import org.simpleframework.xml.*

/**
 * Created by Jasmine Breedlove on 4/19/2018.
 */

// data classes representing xml elements from abc news rss feeds
// need JVMOverloads annotations to prevent constructor exceptions
@Root(name="rss", strict = false)
data class Rss @JvmOverloads constructor(@field:Element(name="channel", required = false)
                                            var channel: Channel = Channel())

@Root(name = "channel", strict = false)
data class Channel @JvmOverloads constructor(@field:Element(name = "title", required = false)
                                             var channelTitle: String = "",
                                             @field:ElementList(name="item", inline = true, required = false)
                                             var articleList: ArrayList<Article> = ArrayList())

@Root(name = "item", strict = false)
data class Article @JvmOverloads constructor(@field:Element(name = "title", required = false) var articleTitle: String = "",
                                             @field:Element(name = "link", required = false) var link: String = "",
                                             @field:Element(name = "pubDate", required = false) var pubDate: String = "",
                                             @field:Element(name = "description", required = false) var description: String = "",
                                             @field:Element(name = "category", required = false) var category: String = "")
