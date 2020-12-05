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
            let titles: [TitleKeyValuePair] = [
                TitleKeyValuePair(key: "Type_Name", value: result.typeName),
                TitleKeyValuePair(key: "Name", value: result.name),
                TitleKeyValuePair(key: "PrevPrice", value: String(describing: result.prevPrice)),
                TitleKeyValuePair(key: "Sec_Subtype", value: result.secSubtype ?? ""),
                TitleKeyValuePair(key: "CouponPercent", value: String(describing: result.couponPercent)),
                TitleKeyValuePair(key: "OfferDate", value: result.offerDate),
                TitleKeyValuePair(key: "isEarlyRepaymentAvailable", value: String(describing: result.isEarlyRepayment)),
                TitleKeyValuePair(key: "initValue", value: String(describing: result.initialValue)),
                TitleKeyValuePair(key: "CouponValue", value: String(describing: result.couponValue)),
                TitleKeyValuePair(key: "MaturityDate", value: result.maturityDate),
                TitleKeyValuePair(key: "AccumulatedCouponIncome", value: String(describing: result.accumulatedCouponIncome)),
                TitleKeyValuePair(key: "Duration", value: String(describing: result.duration)),
                TitleKeyValuePair(key: "ListLevel", value: String(describing: result.listLevel))
            ]
            List {
                HStack {
                    Image("ic_info")
                            .frame(width: 15, height: 15)
                    BonderText(text: "Информация о выпуске", size: 14, textColor: TextColor.purple)
                }
                .listRowInsets(EdgeInsets(top: 0, leading: 0, bottom: 40, trailing: 0))
                
                ForEach(titles, id: \.self) { item in
                    HStack {
                        BonderText(text: item.key)
                        Spacer()
                        BonderText(text: item.value)
                    }
                }
            }
        case is BondDetailsState.Error:
            ErrorView()
        default:
            Text("А зочем дефольт?")
        }
    }
}

struct BondDetailsVIew_Previews: PreviewProvider {
    static var previews: some View {
        BondDetailsView(details: "", proxy: BondsDetailsProxy(bondListEntity: BondListEntity(
                    firstLetter: "x", secId: "RU000A0JNYN1", bondName: "IГор.Обл.Займ Москвы 48 в. мамку твою ебал в", couponPercent: 6.5, prevPrice: 8.7, maturityDate: "MaturityDate", offerDate: "OfferDate", accumulatedCouponIncome: 123.0, duration: 56, listLevel: 123, couponValue: 1.23
        )))
    }
}

/*BondListEntity(
        firstLetter: "X",
        secId: "RU000A0JNYN1",
        bondName: "IГор.Обл.Займ Москвы 48 в. мамку твою ебал в",
        couponPercent: 6.0,
        prevPrice: 101.94,
        maturityDate: "2022 - 06 - 11"
)*/

struct TitleKeyValuePair: Hashable {

    var key: String
    var value: String

    func hash(into hasher: inout Hasher) {
        hasher.combine(key)
        hasher.combine(value)
    }

    init(key: String, value: String) {
        self.key = key
        self.value = value
    }
}
