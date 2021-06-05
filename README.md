### Android Seven Segment View
WARNING: This library is still a WIP and therefor it is not considered stable.

A view that displays numerical digits like an old fashioned [7 Segment LCD display](https://en.wikipedia.org/wiki/Seven-segment_display)

![](https://github.com/JackHurst0/seven-segment-display/blob/master/demo.gif)


### Usage
Add Jitpack distribution to the end of the repositories in your root build.gradle file.

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
    
Add the dependency to the list of dependencies in your app build.gradle file

	dependencies {
	        implementation 'com.github.JackHurst0:seven-segment-display:0.0.2'
	}
   
#### Customization
Use `app:digits` to set the content of the view in xml, or `view.digits` to set them in code

Use `app:onColor` and `app:offColor` to customize the colors of the segments.

Use `app:segmentLength` and `app:segmentThickness` to control the size of the digits in the view.

Use `app:digitSpacing` to control how digits are spaced from each other.

Use `app:minDisplay` to pad the view with leading leading zeros to be as long as you specify