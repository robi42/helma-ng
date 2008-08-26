load("scripts/lib/file.js");

// create new app
var appName = arguments[0];
var appPath = "apps/" + appName;
var appDir = new File(appPath);
var templateDirectory = new File("generators/app");


function copyDirectory(dir, dest) {
   var arr = dir.list(/^[^.].*/); // ignore hidden files
   var dest = new File(dest);
   if (dest.exists()) {
      print("exists " + dest);
   } else {
      print("create " + dest);
      dest.makeDirectory();
   }
   if (!dest.isDirectory()) throw Error("Can't copy " + dir + " to " + dest + " - it's not a directory.");
   
   for (var i=0; i<arr.length; i++) {
      var f = new File(dir, arr[i]);
      var d = new File(dest, arr[i]); // destination
      if (f.isDirectory()) {
         copyDirectory(f, d);
      } else {
         if (d.exists()) {
            print("exists " + d);          
         } else {         
            print("create " + d);
            f.hardCopy(d);
         }
      }
   }
}

copyDirectory(templateDirectory, appDir);

quit();
