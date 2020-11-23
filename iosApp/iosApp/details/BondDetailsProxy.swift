import shared

class BondsDetailsProxy: ObservableObject {

    init(secId: String) {
        let viewModel = BondDetailsViewModel(secId: secId, bonderRepository: ServiceLocator().bonderRepository)
        proxyState = viewModel.state.origin.value as! BondDetailsState

        viewModel.state.watch { state in
            self.proxyState = state!
        }
    }

    @Published var proxyState: BondDetailsState

}