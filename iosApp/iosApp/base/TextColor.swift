import SwiftUI

enum TextColor: CaseIterable {
    case grey
    case black
    case purple

    var uiColor: UIColor! {
        switch self {
        case .grey:
            return UIColor(named: "84898C")
        case .black:
            return UIColor(named: "333333")
        case .purple:
            return UIColor(named: "5222D0")
        }
    }
}