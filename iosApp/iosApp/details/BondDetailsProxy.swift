import shared

class BondsDetailsProxy: ObservableObject {

    init(secId: String) {
        let viewModel = BondDetailsViewModel(secId: secId, bonderApi: ServiceLocator().bonderApi)
        proxyState = viewModel.state.origin.value as! BondDetailsState

        viewModel.state.watch { state in
            self.proxyState = state!
        }
    }

    @Published var proxyState: BondDetailsState

}