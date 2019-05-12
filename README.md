<h1> Project Details: </h1> 
A interactive mobile application that allows users to load, display images in a dynamic layout.
A rating system is in place to allow users to rate (1-5), and filter images. Mobile implementation of A3.

<ul>
    <li> The application starts with a toolbar with clear, load, filter, reset buttons, and an empty list (image grid). </li>
    <li> The toolbar has a clear button, load button, and a filter widget </li> 
    <li> Filter images will allow users to show images that have atleast that filter rating </li>
    <li> Supports both JPG and PNG images, in the sample loaded images (1 images is a PNG (last image), the rest are JPG) </li>
    <li> Supports two layouts: vertical (1 column, and as many rows as required), horizontal (2 rows and as many columns as required) </li>
    <li> Adding images can be done by pressing load or selecting floating action bar at bottom left of screen and searching for image URI </li>
    <li> Adding images adds more rows when required, shows vertical scrollbar (no horizontal scrollbar) </li>
    <li> Pressing on image will show a full-screen popup of image, pressing on image again, will go back to the galary </li> 
    <li> Rotating phone to landscape mode will change layout, and rotating to vertical (portrait) will also change layout. </li>
    <li> Images can be rated from (1-5) and also cleared back to 0. </li> 
</ul>

The application can be run using the .apk file which is in the a4 folder. 

<h1> Enhancements </h1>
Adding a search button (bottom left of application), users can enter a URI of an image on the web. 
If the image exists, it will be loaded and added to bottom of the list. Search can be done multiple times.


<h1> Developement Env </h1> 
Developed in android studio 3.1.3, and using SDK 26. Ran using Pixel AVD using API 26. 

<h1> Summary: </h1> 
<b> Load Icon: </b> Loads 10 default images (9 JPG, 1 PNG). <br> 
<b> Filter Stars: </b> Can select a number of starts to filter by. (Will show images with a rating >= filter value selected) <br> 
<b> Vertical Layout </b> Achieved by rotating device from landscape (horizontal) mode, number of columns changes <br> 
<b> Horizontal layout </b> Achieved by rotating device from vertical (portrait) mode, number  of columns changes <br> 
<b> Thumbnail Image </b> Can press on the image to open a full-screen popup of image, click image again to return back to galary. <br> 
<b> Thumbnail Stars </b> Can select a certain number of stars to rate the image out of 5. This will be used to filter. <br> 
<b> Thumbnail Stars reset </b> Can hit button to clear rating for the particular image <br> 
<b> Enhancement: </b> Search URI of image to add to list <br> 
<b> Dev environment: </b> Android Studio 3.1.3, SDK 26, Pixel AVD using API 26 </br>
