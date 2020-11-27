import SwiftUI

enum TextColor: CaseIterable {
    case grey
    case black

    var uiColor: UIColor! {
        switch self {
        case .grey:
            return UIColor(named: "84898C")
        case .black:
            return UIColor(named: "333333")
        }
    }
}