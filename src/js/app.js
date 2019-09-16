// Import Framework7
import Framework7 from 'framework7/framework7.esm.bundle.js';

// Import Framework7-React Plugin
import Framework7React from 'framework7-react';


// Import Icons and App Custom Styles
import fontawesome from '@fortawesome/fontawesome'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDirections } from "./custom-icons/icons";
fontawesome.library.add(faDirections)

import '../css/icons.css';
import '../css/app.less';


// Import App Component
import App from '../components/app.jsx';

// Init F7 React Plugin
Framework7.use(Framework7React)

import * as F7 from 'framework7-react';

import('../js/cordova-app').then((cordovaApp) => {
  window.cordovaApp  = cordovaApp;
    });


// Mount React App
window.App = App;
window.FontAwesomeIcon = FontAwesomeIcon;
window.F7 = F7;
window.F7App = Framework7;

/*ReactDOM.render(
  React.createElement(App),
  document.getElementById('app'),
);*/