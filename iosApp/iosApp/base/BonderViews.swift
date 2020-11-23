import SwiftUI

struct BonderText: View {

    var text: String
    var size: CGFloat = 12

    var body: some View {
        Text(text)
            .font(.custom("IgraSans.otf", size:size))
    }
}

struct BonderText_Previews: PreviewProvider {
    static var previews: some View {
        BonderText(text: "Республика Беларусь выпуск 1",size: 48)
    }
}
