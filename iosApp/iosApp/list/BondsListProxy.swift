import shared
import SwiftUI

class BondsListProxy: ObservableObject {

    // Тест успешного ответа с данными
    /*init() {
        proxyState = BondsListState.Success(
                result: [
                    BondListModel(
                            firstLetter: "X",
                            secId: "RU000A0JNYN1",
                            bondName: "IГор.Обл.Займ Москвы 48 в. мамку твою ебал в",
                            couponPercent: 6.0,
                            prevPrice: 101.94,
                            maturityDate: "2022 - 06 - 11"
                    ),
                    BondListModel(
                            firstLetter: "X",
                            secId: "RU000A0JNYN1",
                            bondName: "IГор.Обл.Займ Москвы 48 в. мамку твою ебал в очело Сорян если что, не хотел",
                            couponPercent: 6.0,
                            prevPrice: 101.94,
                            maturityDate: "2022 - 06 - 11"
                    ),
                    BondListModel(
                            firstLetter: "X",
                            secId: "RU000A0JNYN1",
                            bondName: "IГор.Обл.Займ Москвы 48 в. мамку твою ебал в очело Сорян если что, не хотел",
                            couponPercent: 6.0,
                            prevPrice: 101.94,
                            maturityDate: "2022 - 06 - 11"
                    ),
                    BondListModel(
                            firstLetter: "X",
                            secId: "RU000A0JNYN1",
                            bondName: "IГор.Обл.Займ Москвы 48 в. мамку твою ебал в очело Сорян если что, не хотел",
                            couponPercent: 6.0,
                            prevPrice: 101.94,
                            maturityDate: "2022 - 06 - 11"
                    ),
                    BondListModel(
                            firstLetter: "X",
                            secId: "RU000A0JNYN1",
                            bondName: "IГор.Обл.Займ Москвы 48 в. мамку твою ебал в очело Сорян если что, не хотел",
                            couponPercent: 6.0,
                            prevPrice: 101.94,
                            maturityDate: "2022 - 06 - 11"
                    ),
                    BondListModel(
                            firstLetter: "X",
                            secId: "RU000A0JNYN1",
                            bondName: "IГор.Обл.Займ Москвы 48 в. мамку твою ебал в очело Сорян если что, не хотел",
                            couponPercent: 6.0,
                            prevPrice: 101.94,
                            maturityDate: "2022 - 06 - 11"
                    )
                ]
        )
    }*/

    // Боевой код
    init() {
        let viewModel = BondsListViewModel(bonderRepository: ServiceLocator().bonderRepository)
        proxyState = viewModel.state.origin.value as! BondsListState

        viewModel.state.watch { state in
            self.proxyState = state!
        }
    }

    @Published var proxyState: BondsListState

}

struct BondsListProxy_Previews: PreviewProvider {
    static var previews: some View {
        /*@START_MENU_TOKEN@*/Text("Hello, World!")/*@END_MENU_TOKEN@*/
    }
}
