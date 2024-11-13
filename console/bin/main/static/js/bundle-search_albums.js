/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "./src/main/scss/search.css":
/*!**********************************!*\
  !*** ./src/main/scss/search.css ***!
  \**********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
// extracted by mini-css-extract-plugin


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
/*!**************************************!*\
  !*** ./src/main/js/search_albums.js ***!
  \**************************************/
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "searchAlbum": () => (/* binding */ searchAlbum)
/* harmony export */ });
/* harmony import */ var _scss_search_css__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../scss/search.css */ "./src/main/scss/search.css");


const searchInput = document.getElementById('searchValue')
const searchResults = document.getElementById('searchResults')
const table = document.getElementById('table')
const tableBody = searchResults.querySelector('tbody')

async function searchAlbum() {
    const searchTerm = searchInput.value
    if (searchTerm && searchTerm.trim().length >= 3) {
        const response = await fetch(`api/albums?searchValue=${searchTerm}`, {
            method: 'GET',
            headers: {
                Accept: 'application/json'
            }
        })
        if (response.status === 200) {
            const album = await response.json()
            searchResults.style.visibility = 'visible'
            table.style.visibility = 'hidden'
            album.forEach((object) => {
                tableBody.innerHTML += `
                    <tr>
                      <td>${object.title}</td>
                      <td>${object.amountOfSongs}</td>
                      <td>${object.releaseDate}</td>
                    </tr>`
            })
        }
        if (response.status !== 200) {
            // eslint-disable-next-line no-alert
            alert('No matching results')
        }
    }
}

searchInput.addEventListener('input', searchAlbum)

})();

/******/ })()
;
//# sourceMappingURL=bundle-search_albums.js.map