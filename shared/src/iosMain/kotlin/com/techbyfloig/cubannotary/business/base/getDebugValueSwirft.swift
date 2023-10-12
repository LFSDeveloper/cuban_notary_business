@_cdecl("getDebugValueFromSwift")
public func getDebugValueFromSwift() -> Bool {
    #if DEBUG
        return true
    #else
        return false
    #endif
}