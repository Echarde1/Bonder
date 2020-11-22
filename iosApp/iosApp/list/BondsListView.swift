import SwiftUI
import shared

func greet() -> String {
    Greeting().greeting()
}

struct BondsListView: View {

    @ObservedObject var proxy: BonsListProxy

    var body: some View {
        let state = self.proxy.proxyState

        switch state {
        case is BondsListState.Loading:
            Text("Loooooooading")
        case is BondsListState.Success:
            let result: [ListBond] = (state as! BondsListState.Success).result
            NavigationView {
                List(result, id: \.secid) { bond in
                    NavigationLink(destination: BondDetailsView(details: bond.description(), proxy: BondsDetailsProxy(secId: bond.secid))) {
                        Text(bond.description())
                        Spacer()
                    }
                }
            }
            .navigationBarTitle("Здарова епта")
        case is BondsListState.Error:
            Text("Ошибочка")
        default:
            Text("А зочем дефольт?")
        }
    }
}

struct BondsListView_Previews: PreviewProvider {
    static var previews: some View {
        BondsListView(proxy: BonsListProxy())
    }
}
