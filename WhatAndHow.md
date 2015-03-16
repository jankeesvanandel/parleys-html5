# What? #

What would be the benefit of having an HTML5 version of the Parleys.com Player?

  * We could use this version as a graceful degradation strategy when Flash is not available
  * We could also use this version for any HTML5 enabled devices (iPhone, iPad, ...)

What are the disadvantages of doing an HTML5 version?

  * The HTML5 

&lt;video&gt;

 tag does not support the Adobe Flash Video codec (FLVs)
  * Safari and IE9 (H.264/MPEG-4) Vs Firefox and Opera (Ogg Theora)
  * HTML5 is still a moving target and is expected to be finalized in 2022!

# How? #

Lets discuss our options in more detail...

## No support for Adobe Flash Video (FLV) format ##

Today Parleys.com uses the Adobe Flash Video format (FLV).

The HTML5 

&lt;video&gt;

 tag supports the Ogg Theora and H.264/MPEG-4 codecs (see [wikipedia table](http://en.wikipedia.org/wiki/HTML5_video).

The next major release of the [Publisher](http://www.parleys.com/publisher) will support H.264/mpeg-4 and our Player already supports H.264, just have a look at the [Parleys tutorials](http://www.parleys.com/#st=4&id=56141) to see it in action.

This means we could consider migrating the existing FLV videos to H.264/mpeg-4.

## Which browser? ##

When choosing H.264 this means we can support both Safari, Google Chrome and IE9.  Keep in mind that his version of the player does not replace the existing Flex/AIR players but is the graceful degradation strategy.

In a first phase we will focus on Safari making this version suitable for the Apple iPad  :)

## Does the 

&lt;video&gt;

 tag support cue points? ##

Parleys.com is dependent on video cue points, so we know when to show the correct slide.

I've made a small [mockup](http://staging.parleys.com/video.html) (Mac Safari only for now), which demonstrates that this is possible in HTML5 using the 

&lt;video&gt;

 tag and some JavaScript...  so what's next?

## Which web framework have we used? ##

Parleys has only a hand full pages ([home](http://www.parleys.com), profile, [the player](http://www.parleys.com/#st=5&id=1616) and [overview pages](http://www.parleys.com/#st=3&id=189)) so we could develop this in pure HTML5 and Javascript.

Personally I prefer to approach this in a more component based manner and with Jan-Kees van Andel on board (he's a JSF myfaces committer) it makes sense to give JSF2 the opportunity.

However if you prefer another web framework (just pick one from the 300 out there) you could consider starting your own (open source) project. You can reuse our HTML5 code and we'll be more than happy to host your end result on Parleys.com (make sure you let us know).

## How will we integrate the HTML5 client? ##

The current Parleys.com server has a simple [REST](REST.md) like interface which is used by our existing iPhone application and was also used by our experimental JavaFX and GWT clients.

Any JSON-aware client can leverage this interface, so this shouldn't be problems.

## Lets get the community involved in our HTML5 Quest! ##

Parleys.com has already reached over 600.000 developers who enjoy presentations on a regular basis.

HTML5 and Javascript is so transparent that we've considered making this project open-source, hosting it on Google Code and hopefully getting some extra help from our Parleys Community!

If you want to enjoy Parleys on the iPad (and beyond) then please consider helping out... lets make this happen!

#### "Alone we can do so little; together we can do so much." - Helen Keller ####