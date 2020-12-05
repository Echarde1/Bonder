import shared

class BondsDetailsProxy: ObservableObject {

    init(bondListEntity: BondListEntity) {
        proxyState = BondDetailsState.Success(result: BondDetailsEntity(
                typeName: "TYPE_NAME", name: "NAME", prevPrice: 5.43, secSubtype: nil, couponPercent: 123.123, couponValue: 321.321, offerDate: "OfferDate", isEarlyRepayment: false, initialValue: 123, maturityDate: "MaturityDate", accumulatedCouponIncome: 654.456, duration: 332, listLevel: 80
        ))
    }

    /*init(bondListEntity: BondListEntity) {
        let viewModel = BondDetailsViewModel(bondListEntity: bondListEntity, bonderRepository: ServiceLocator().bonderRepository)
        proxyState = viewModel.state.origin.value as! BondDetailsState

        viewModel.state.watch { state in
            self.proxyState = state!
        }
    }*/

    @Published var proxyState: BondDetailsState

}