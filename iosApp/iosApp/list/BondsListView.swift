import SwiftUI
import shared

func greet() -> String {
    Greeting().greeting()
}

struct BondsListView: View {

    @ObservedObject var proxy: BondsListProxy

    var body: some View {
        let state = self.proxy.proxyState

        switch state {
        case is BondsListState.Loading:
            BondsListLoadingView()
        case is BondsListState.Success:
            let result: [BondListEntity] = (state as! BondsListState.Success).result
            if result.isEmpty {
                BonderText(text: "По вашему запросу ничего не найдено")
            } else {
                NavigationView { // Да-да. Костыль, чтоб не было стрелочки
                    List(result, id: \.secId) { bond in
                        ZStack { // Да-да. Костыль, чтоб не было стрелочки
                            BondsListRow(model: bond)
                            NavigationLink(destination: getDestination(bondListEntity: bond)) { // Да-да. Костыль, чтоб не было стрелочки
                                EmptyView() // Да-да. Костыль, чтоб не было стрелочки
                            } // Да-да. Костыль, чтоб не было стрелочки
                        } // Да-да. Костыль, чтоб не было стрелочки
                    }
                            .listSeparatorStyle(style: .none)
                            .navigationBarHidden(true) // Чтоб не было отступа от верхнего края
//                        .edgesIgnoringSafeArea([.top, .bottom]) Еще вот этим можно попробовать воспользоваться при проблемах
                } // Да-да. Костыль, чтоб не было стрелочки
            }
        case is BondsListState.Error:
            ErrorView()
        default:
            Text("А зочем дефольт?")
        }
    }
}

struct BondsListRow: View {

    var model: BondListEntity

    var body: some View {
        HStack {
//            BonderText(text: model.firstLetter)
//                    .background(Circle().frame(width: 32, height: 32).foregroundColor(Color(BondImageColor.getRandomColor())))
//                    .padding(.trailing, 14)
            Circle().frame(width: 32, height: 32).foregroundColor(Color(BondImageColor.getRandomColor()))
//                    .padding(.trailing, 14)
            VStack {
                HStack {
                    BonderText(text: model.bondName, textColor: TextColor.black)
                            .lineLimit(1)
                    Spacer()
                    BonderText(text: "\(model.couponPercent) %", size: 14, textColor: TextColor.black)
                }
                HStack {
                    BonderText(text: model.maturityDate, size: 9)
                    Spacer()
                    BonderText(text: "\(model.prevPrice * 10) ₽", size: 9)
                }.padding(.top, 0.5)
            }
        }
    }

    enum BondImageColor: CaseIterable {
        case pink
        case yellow
        case purple

        static func getRandomColor() -> UIColor! {
            let colors = BondImageColor.allCases
            let lastIndex = BondImageColor.allCases.endIndex
            let idx = Int.random(in: 0..<lastIndex)

            return colors[idx].color
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

struct BondsListLoadingView: View {

    var body: some View {
        List((1...20), id: \.self) { _ in
            HStack {
                Circle().frame(width: 32, height: 32).foregroundColor(Color(viewStubColor))
                        .padding(.trailing, 14)
                VStack {
                    HStack {
                        Spacer().frame(width: 200, height: 12).background(Color(viewStubColor))
                        Spacer()
                        Spacer().frame(width: 40, height: 12).background(Color(viewStubColor))
                    }
                    HStack {
                        Spacer().frame(width: 100, height: 12).background(Color(viewStubColor))
                        Spacer()
                        Spacer().frame(width: 26, height: 12).background(Color(viewStubColor))
                    }.padding(.top, 0.5)
                }
            }
        } // .listSeparatorStyle(style: .none) не работает
    }

    private let viewStubColor: UIColor! = UIColor(named: "DDDDDD")

}

struct BondListView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            BondsListView(proxy: BondsListProxy())
        }
    }
}

private func getDestination(bondListEntity: BondListEntity) -> BondDetailsView {
    BondDetailsView(
            details: bondListEntity.description(),
            proxy: BondsDetailsProxy(bondListEntity: bondListEntity)
    )
}
