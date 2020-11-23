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
                    let model = BondsListRow.BondListRowModel(
                            secId: bond.secid,
                            bondName: bond.sec_name,
                            maturityDate: bond.maturity_date,
                            couponPercent: bond.coupon_percent,
                            prevPrice: bond.prev_price
                    )
                    BondsListRow(model: model)
                }
            }
        case is BondsListState.Error:
            Text("Ошибочка")
        default:
            Text("А зочем дефольт?")
        }
    }
}

struct BondsListRow: View {

    var model: BondListRowModel

    var body: some View {
        HStack {
            Text(String(model.bondName.first!))
                    .background(Circle()
                            .foregroundColor(Color(BondColor.getRandomColor()))
                            .frame(width: 32, height: 32)
                    )
            VStack {
                BonderText(text: model.bondName).lineLimit(1)
                Text(model.maturityDate)
            }
            VStack {
                Text("\(model.couponPercent) %")
                Text("\(model.prevPrice * 10) ₽")
            }
        }.frame(maxWidth: .infinity)
        Spacer()
        NavigationLink(
                destination: BondDetailsView(details: model.description(),
                        proxy: BondsDetailsProxy(secId: model.secId))
        ) {
            EmptyView()
        }
    }

    struct BondListRowModel {

        let secId: String
        let bondName: String
        let maturityDate: String
        let couponPercent: Double
        let prevPrice: Double

        func description() -> String {
            "secId: \(secId)\nbondName:\(bondName)\nmaturityDate:\(maturityDate)\ncouponPercent:\(couponPercent)\nprevPrice:\(prevPrice)"
        }
    }

    enum BondColor: CaseIterable {
        case pink
        case yellow
        case purple

        static func getRandomColor() -> UIColor! {
            let lastIndex = BondColor.allCases.endIndex
            let idx = Int.random(in: 0..<lastIndex)

            return BondColor.allCases[idx].color
        }

        private var color: UIColor! {
            switch self {
            case .pink:
                return UIColor(named: "FFB0B0")
            case .yellow:
                return UIColor(named: "FFF0D3")
            case .purple:
                return UIColor(named: "BBBBFF")
            }
        }
    }
}

struct BondRowView_Previews: PreviewProvider {
    static var previews: some View {
        BondsListRow(model: BondsListRow.BondListRowModel(secId: "Sec123123", bondName: "Республика Беларусь выпуск 1", maturityDate: "22-22-2222", couponPercent: 105.43, prevPrice: 111.113))
    }
}
