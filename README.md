# HW4 --Shopping list
Android Project

Master is the main branch of this project. All the other branches have not been deleted since they can be used as proof against plagiarism. 
The database of the app can be prepolulated as long as the user navigate to DB > ShoppingDatabase and adds .addCallback(roomCallback) before .build().
The app should run without any issues as long as the Android studio is configured properly and an emulator/ device is present. 

Content:  
  The app starts empty with a floating action button to add list of shopping composed of image (optional) and name. Each list can hold many products that can be added with 
  another floating action button. A product is composed of a name, quantity and the type (unit, kg, liter). If a product is clicked, it can be deleted and edited. 
  A shopping list can be deleted as well, if long clicked. 

Testing:
  Please test each testing one at a time, otherwise there is a risk of failing them. I decided to add time sleep in few cases to avoid the app going in the wrong course. 
