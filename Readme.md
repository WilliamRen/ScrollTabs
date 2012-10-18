Scroll Tabs
===

Android tabs for easy implementation and customization. Requires the use of ViewPager.

***
![img](https://github.com/pietrocaselani/ScrollTabs/blob/master/Images/ScrollTabs.png?raw=true)
***

Usage
---

1. Set the view tabs as the example on your layout. This must be configured with the `ViewPager`.

        <com.pc.ScrollableTabView.ScrollableTabView
        	android:id="@+id/tabs"
        	android:layout_width="fill_parent"
        	android:layout_height="wrap_content" />
        	
2. In your activity, add on the method "onCreate" the following code:

		ScrollableTabView scrollableTabView = (ScrollableTabView)
		findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        scrollableTabView.setTabAdapter(new MyTabAdapter());
        scrollableTabView.setViewPager(pager);
        
3. If you need, you can listen to page view and tab changes notification:

		scrollableTabView.setOnPageChangeListener(this);
		scrollableTabView.setTabListener(this);
		
Theming
---

1. You can style the tabs creating a style that inherits from Tab

		<style name="MyTabs" parent="Tab">
	        <item name="android:textAppearance">@style/MyTextAppearance</item>
        </style>
        <style name="MyTextAppearance" parent="Tab.TextAppearance">
        	<item name="android:textSize">22sp</item>
        	<item name="android:textColor">@color/my_textcolor</item>
        </style>
        
2. Use the [Android Action Bar Style Generator](http://jgilfelt.github.com/android-actionbarstylegenerator/#name=example&compat=sherlock&theme=light&actionbarstyle=solid&backColor=4a464a%2C100&secondaryColor=ffffff%2C100&tertiaryColor=db15db%2C100&accentColor=0e7015%2C100) to change the background.
3. Create a layout using `TabView`:

		<com.pc.ScrollableTabView.TabView
			style="@style/MyTabs" />



Developed By
============

Pietro Caselani - [pc1992@gmail.com](pc1992@gmail.com)