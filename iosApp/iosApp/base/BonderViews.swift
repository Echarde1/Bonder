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

struct BonderText_Previews: PreviewProvider {
    static var previews: some View {
        BonderText(text: "Республика Беларусь выпуск 1", size: 48)
    }
}
