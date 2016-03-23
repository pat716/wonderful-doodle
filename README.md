# wonderful-doodle

This drawing app prototype was built using [Android Studio](http://developer.android.com/sdk/index.html?gclid=CjwKEAjw_ci3BRDSvfjortr--DQSJADU8f2jlIC6xbvMKN_ytRR2n6k0DDXhrn_M89zavaoyjZEzBBoCPXTw_wcB) with no external libraries used besides the default toolset included with the software. 

# Environment

The minimum Android version supported by this app is Android 4.1 Jelly Bean (API Level 16), while the target version is Android 6.0 Marshmallow (API Level 23). Testing was performed using an emulated Nexus 6P running Android 6.0 x86. 

# Running

This app should be run by importing the source code (all of which is included in this repository) into a new project in Android Studio with the settings described above, performing the default Gradle build process by selecting Build->Make Project in the Android Studio IDE, and running the app using a real or virtual Android device that mets the minimum specifications.

Drawing is enabled as soon as the app is initialized, with a white background and a default stroke color of black. The drawing can be cleared by pressing the "CLEAR" button on the bottom right. The color menu, which appears when the "COLOR" button in the bottom left is pressed, can be used to change the individual RGB components of the stroke color. Tapping the button again will return to the drawing interface. The "BRUSH" menu in the bottom center of the screen allows for the alpha (transparency) and stroke width to be changed. Again, tapping the button again will return the user to the drawing interface.

The undo button on the top left will undo the last action (if one is present), and the redo button on the top right will restore the last action that was removed via the undo button. These buttons should probably change visibility based on whether interacting them will currently have any effect, but I ran into issues when trying to implement that and ultimately decided their functionality was clear enough as is. Also of note is that using the "CLEAR" button will empty the undo and redo data structures, so clearing a doodle is permanent.

# References

The resources I consulted while building this project are as follows:

    The videos provided to us on the assignment page were very helpful in getting started.
        Froehlich, J. [Jon Froehlich]. (2016, March 16). *Rapidly Prototyping a Calculator App in Android.* Retrieved from https://youtu.be/2-mmH_nOE9Q
        Froehlich, J. [Jon Froehlich]. (2014, October 24). *Quick Intro to Creating a Custom View in Android.* Retrieved from https://youtu.be/ktbYUrlN_Ws
  
    The following posts on the OnSeekBarChangeListener functionality were instrumental to helping me get my SeekBar views working properly.
        http://stackoverflow.com/questions/3930942/how-can-i-use-seekbars-onseekbarchangelistener-to-seek-to-a-specific-point-in-a
        http://stackoverflow.com/questions/8956218/android-seekbar-setonseekbarchangelistener
        
    And of course, the Android API Documentation itself, specifically the entries for View, Paint, and SeekBar.
        http://developer.android.com/reference/android/view/View.html
        http://developer.android.com/reference/android/graphics/Paint.html
        http://developer.android.com/reference/android/widget/SeekBar.html
  
