# LicenseView
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.pcchin.customdialog/customdialog/badge.svg)](https://search.maven.org/artifact/com.pcchin.customdialog/customdialog)

## Library Info
This library contains 2 DialogFragments: DefaultDialogFragment and DismissibleDialogFragment.

DefaultDialogFragment aims to make DialogFragment easier to implement and migrate from AlertDialogs.
 It also includes the ability to set whether the dialog can be rotatable through `setRotatable(boolean rotatable)`. 
 The tag for the fragment is also different than the tag in the DialogFragment, and would require `setFragmentTag(String tag)` and `getFragmentTag()` to set and get it.

DismissibleDialogFragment is an extension of DefaultDialogFragment, which overrides the buttons and the onShowListener provided in the AlertDialog.
 The dialog would not be automatically dismissed through button presses. Thus, it can only be dismissed directly through `dialog.dismiss()`.
 The onShowListener for the AlertDialog can be added by overriding the `onDialogShown(Dialog dialog)` method for 

## Installation
This library is available in Maven Central and a backup is available on my personal repository. To install, you would need to include the following into your `project/build.gradle`:

```
implementation 'com.pcchin.customdialog:customdialog:1.0.3'
```

You may also need to include the following in your `build.gradle`:

```
buildscript {
    ...
}

allprojects {
    ...
    repositories {
        ...
        // Use this if Maven Central is not working
        // maven { url "https://nexus.pcchin.com/repository/maven-releases/" }
        mavenCentral()
    }
}
```

## Usage
The usage for the fragments can be found in the [Wiki](https://github.com/pcchin/customdialog/wiki).

## Example Implementation
Download the example APK [here](/example.apk).
![Example Implementation](/example-implementation.png)

## Contribution
Any contribution is welcome, feel free to add any issues or pull requests to the repository.

## License
This library is licensed under the [Apache 2.0 License](/LICENSE).