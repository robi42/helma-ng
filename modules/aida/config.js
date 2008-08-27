var __shared__ = true;
importFromModule("config.default", "*");
 
(function () {

   importModule("helma.logging", "logging");
   var logger = logging.getLogger(__name__);

   this.ENVIRONMENT = null;

   setEnvironment = function(name) {
      this.ENVIRONMENT = name;
      importFromModule("config.environments." + name + "." + name, "*");
      logger.info("Loaded configuration for environment " + name);
   }

   getEnvironment = function() {
      return this.ENVIRONMENT;
   }

}).call(this); 
