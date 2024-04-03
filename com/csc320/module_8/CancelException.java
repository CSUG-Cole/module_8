package com.csc320.module_8;

/* Exception to unwind the stack when performing add/remove operations
 * during main event loop.
 */
class CancelException extends Exception {
    public CancelException() {
        super("User cancelled an add/remove operation.");
    }
}
