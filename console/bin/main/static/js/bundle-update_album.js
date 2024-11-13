/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "./src/main/js/modules/csrf.js":
/*!*************************************!*\
  !*** ./src/main/js/modules/csrf.js ***!
  \*************************************/
/***/ ((__unused_webpack___webpack_module__, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "getCsrfInfo": () => (/* binding */ getCsrfInfo)
/* harmony export */ });
function getCsrfInfo() {
    const header = document.querySelector('meta[name="_csrf_header"]').content
    const token = document.querySelector('meta[name="_csrf"]').content
    return {
        [header]: token
    }
}


/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		var cachedModule = __webpack_module_cache__[moduleId];
/******/ 		if (cachedModule !== undefined) {
/******/ 			return cachedModule.exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId](module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/************************************************************************/
/******/ 	/* webpack/runtime/define property getters */
/******/ 	(() => {
/******/ 		// define getter functions for harmony exports
/******/ 		__webpack_require__.d = (exports, definition) => {
/******/ 			for(var key in definition) {
/******/ 				if(__webpack_require__.o(definition, key) && !__webpack_require__.o(exports, key)) {
/******/ 					Object.defineProperty(exports, key, { enumerable: true, get: definition[key] });
/******/ 				}
/******/ 			}
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/hasOwnProperty shorthand */
/******/ 	(() => {
/******/ 		__webpack_require__.o = (obj, prop) => (Object.prototype.hasOwnProperty.call(obj, prop))
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/make namespace object */
/******/ 	(() => {
/******/ 		// define __esModule on exports
/******/ 		__webpack_require__.r = (exports) => {
/******/ 			if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 				Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 			}
/******/ 			Object.defineProperty(exports, '__esModule', { value: true });
/******/ 		};
/******/ 	})();
/******/ 	
/************************************************************************/
var __webpack_exports__ = {};
// This entry need to be wrapped in an IIFE because it need to be isolated against other modules in the chunk.
(() => {
/*!*************************************!*\
  !*** ./src/main/js/update_album.js ***!
  \*************************************/
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "saveAlbum": () => (/* binding */ saveAlbum)
/* harmony export */ });
/* harmony import */ var _modules_csrf_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./modules/csrf.js */ "./src/main/js/modules/csrf.js");


const saveButton = document.querySelector('.btn-primary')
// eslint-disable-next-line consistent-return
async function saveAlbum() {
    const row = saveButton.parentNode.parentNode
    const id = row.getAttribute('album-id')

    const response = await fetch(`/api/albums/${id}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            ...(0,_modules_csrf_js__WEBPACK_IMPORTED_MODULE_0__.getCsrfInfo)()
        },
        body: JSON.stringify({ name: document.querySelector('input').value })
    })
    if (response.status === 200) {
        const album = await response.json()
        window.location.reload()
        // eslint-disable-next-line no-alert
        return album
    }
    if (response.status !== 200) {
        // eslint-disable-next-line no-alert
        alert(`Error: ${response}`)
    }
}

saveButton.addEventListener('click', saveAlbum)

})();

/******/ })()
;
//# sourceMappingURL=bundle-update_album.js.map