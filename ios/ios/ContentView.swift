//
//  ContentView.swift
//  ios
//
//  Created by Jorge Martin on 16/05/2024.
//

import SwiftUI
import common

struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text(GetPlatformNameKt.getPlatformName())
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
