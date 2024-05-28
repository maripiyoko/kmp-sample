package data

data class FakeCardItem(
    val title: String,
    val summary: String,
) {
    companion object {
        fun fakes() = listOf(
            FakeCardItem(
                title = "Lorem ipsum dolor sit amet",
                summary = "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
            ),
            FakeCardItem(
                title = "Ut enim ad minim veniam",
                summary = "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            ),
            FakeCardItem(
                title = "Duis aute irure",
                summary = "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
            ),
            FakeCardItem(
                title = "short",
                summary = "短いテキスト"
            )
        )
    }
}
