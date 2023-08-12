package dev.gewooniraj.abundalacaca

import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

object ChatUtil {

	fun prefixFormat(string: String): TextComponent {
		return LegacyComponentSerializer.legacyAmpersand().deserialize("&7[&6AbundaLaCaca&7] &r$string")
			.decoration(TextDecoration.ITALIC, false)
	}

	fun textFormat(string: String): TextComponent {
		return LegacyComponentSerializer.legacyAmpersand().deserialize(string)
			.decoration(TextDecoration.ITALIC, false)
	}
}