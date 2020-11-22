import shared

class BonsListProxy: ObservableObject {

    init() {
        let viewModel = BondsListViewModel(bonderApi: ServiceLocator().bonderApi)
        proxyState = viewModel.state.origin.value as! BondsListState

        viewModel.state.watch { state in
            self.proxyState = state!
        }
    }

    @Published var proxyState: BondsListState

}
