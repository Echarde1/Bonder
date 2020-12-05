import SwiftUI

struct BonderText: View {

    var text: String
    var size: CGFloat = 12
    var textColor: TextColor = TextColor.grey

    var body: Text {
        Text(text)
                .font(.custom("IgraSans", size: size))
                .foregroundColor(Color(textColor.uiColor))
    }
}

struct ErrorView: View {

    var body: some View {
        VStack {
            BonderText(text: "Упс!", size: 28)
                .padding(.bottom, 20)
            BonderText(text: "Кажется, ошибочка вышла...", size: 16)
                .padding(.bottom, 40)
            Image("error")
                    .resizable()
                    .scaledToFit()
        }
    }
}

/*struct BonderText_Previews: PreviewProvider {
    static var previews: some View {
        BonderText(text: "Республика Беларусь выпуск 1", size: 48)
    }
}*/

struct BonderText_Previews: PreviewProvider {
    static var previews: some View {
        ErrorView()
    }
}
