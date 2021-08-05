# THIS LIBRARY IS NO LONGER MAINTAINED. I suggest you check alternatives.

# ProSpotlight
ProSpotlight is an Android library used to onboard users by showcasing specific features in the app.

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
<img src="https://img.shields.io/badge/license-Apache 2.0-green.svg?style=flat">
[![API](https://img.shields.io/badge/API-11%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=11)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Spotlight-green.svg?style=flat)](http://android-arsenal.com/details/1/3730)

# Screen
<img src="https://github.com/rzrasel/rzandroid-java-sdk/blob/217e2619b83a418ca1e83cc2e8d2aa5060547028/rzandjavagit-prospotlightview/art/intro.gif"/>

# Usage
```java
new ProSpotlightView.Builder(this)
        .introAnimationDuration(400)
        .enableRevealAnimation(isRevealEnabled)
        .performClick(true)
        .fadingTextDuration(400)
        .headingTvColor(Color.parseColor("#eb273f"))
        .headingTvSize(32)
        .headingTvText("Love")
        .subHeadingTvColor(Color.parseColor("#ffffff"))
        .subHeadingTvSize(16)
        .subHeadingTvText("Like the picture?\nLet others know.")
        .maskColor(Color.parseColor("#dc000000"))
        .target(view)
        .lineAnimDuration(400)
        .lineAndArcColor(Color.parseColor("#eb273f"))
        .dismissOnTouch(true)
        .dismissOnBackPress(true)
        .enableDismissAfterShown(true)
        .usageId(usageId) //UNIQUE ID
        .show();
```

# Builder Methods

### maskColor(int)
Overlay Color

### target(View)
View to showcase

### introAnimationDuration(long)
Intro animation duration (For Reveal and Fading)

### enableRevealAnimation(boolean)
Enable reveal animation (Only for Lollipop and above)

### fadingTextDuration(long)
Fade in animation duration for spotlight text (Heading and Sub-heading)

### headingTvSize(int)
Size of heading text

### headingTvColor(int)
Color of heading text

### headingTvText(CharSequence)
Text to display in heading

### subHeadingTvSize(int)
Size of sub-heading text

### subHeadingTvColor(int)
Color of sub-heading text

### subHeadingTvText(CharSequence)
Text to display in sub-heading

### setTypeface(Typeface)
Custom font for text in spotlight view

### lineAndArcColor(int)
Color of the spotlight line

### lineAnimDuration(long)
Line animation duration

### performClick(boolean)
Perform a click on target view

### usageId(String)
Unique id for each spotlight

### dismissOnTouch(boolean)
Dismiss spotlight on touch outside

### enableDismissAfterShown(boolean)
Dismiss spotlight on touch outside after spotlight is completely visible

# Configuration Method
```java
//Create global config instance to reuse it
PreSpotlightConfig config = new PreSpotlightConfig();
config.isDismissOnTouch(true);
config.setLineAndArcColor(0xFFFFFFFF);
...
.setConfiguration(config)
```

# Author

[Rz Rasel](https://github.com/rzrasel)

# Proguard rules

```java
-keep class com.rzandjavagit.prospotlightview.** { *; }
-keep interface com.rzandjavagit.prospotlightview.**
-keep enum com.rzandjavagit.prospotlightview.**
```

# Credits
[MaterialIntroView](https://github.com/iammert/MaterialIntroView)

[Rahul Khanna](https://www.linkedin.com/in/rahul-khanna-01705827)

[Suraj Barthy](https://dribbble.com/thesbdesign)

## License
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)
<!--
//https://github.com/29jitender/Spotlight
//https://github.com/iammert/MaterialIntroView
//https://androidexample365.com/an-implementation-of-tap-targets-from-the-material-design-guidelines/
//https://androidexample365.com/a-tap-target-implementation-in-android-based-on-material-design-onboarding-guidelines/ -->
