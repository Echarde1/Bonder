import SwiftUI
import shared

struct BondDetailsView: View {
    let details: String

    @ObservedObject var proxy: BondsDetailsProxy

    var body: some View {
        let state = self.proxy.proxyState

        switch state {
        case is BondDetailsState.Loading:
            Text("Loooooooading")
        case is BondDetailsState.Success:
            let result = (state as! BondDetailsState.Success).result
            Text("\(result.description()) and \(details)")
            Spacer()
        case is BondDetailsState.Error:
            Text("Ошибочка")
        default:
            Text("А зочем дефольт?")
        }
    }
}

struct BondDetailsVIew_Previews: PreviewProvider {
    static var previews: some View {
        Text("Hello, World!")
    }
}
