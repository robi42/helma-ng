
importModule('helma.logging', 'logging');
logging.setConfig(getResource('config/environments/development/log4j.properties').path);
var logger = logging.getLogger(__name__);

importModule('javascript.prototype');

importFromModule("controller.controller", "*");
importFromModule("controller.filters", "*");
importFromModule("controller.helpers", "*");
importFromModule("view.render", "*");
importFromModule("controller.actions", "*");
importFromModule("view.layout", "*");
importFromModule("controller.request", "*");

this.context = {};
