#import "interop.c"

BOOL isAppInDebugMode() {
    return getDebugValueFromSwift();
}