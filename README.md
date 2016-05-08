# CaseFitText
The choice of string with optimal width


Takes textView with fixed width(not wrap_content), array of strings, chouce the optimal length(max of not reduced) 


    TextView textView = findViewById(R.id.text);
    apps.punksta.autofittext.AutoFitUtil.setOptimalWidthText(textView, "short" , "bigger then short", "the biggest text at all")
    //or
    apps.punksta.autofittext.AutoFitUtil.setOptimalWidthText(textView, R.string.my_array)
    
After onGlobalLayout() the optimal width string would be settet as text

# Source
[AutoFitUtil.java](https://github.com/punksta/CaseFitText/blob/master/autofittext/src/main/java/apps/punksta/autofittext/AutoFitUtil.java)
