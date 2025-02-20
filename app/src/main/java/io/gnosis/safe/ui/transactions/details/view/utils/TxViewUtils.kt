package io.gnosis.safe.ui.transactions.details.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import io.gnosis.data.models.AddressInfo
import io.gnosis.data.models.Chain
import io.gnosis.data.models.transaction.ParamType
import io.gnosis.safe.R
import io.gnosis.safe.utils.dpToPx
import io.gnosis.safe.utils.shortChecksumString
import pm.gnosis.model.Solidity
import pm.gnosis.utils.removeHexPrefix

fun Context.getTransferItem(chain: Chain, address: Solidity.Address, amount: String, addressInfo: AddressInfo?): TxTransferActionView {
    val item = TxTransferActionView(this)
    val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    layoutParams.setMargins(0, dpToPx(16), 0, -dpToPx(8))
    item.layoutParams = layoutParams
    item.setActionInfo(
            chain,
            outgoing = true,
            amount = amount,
            logoUri = chain.currency.logoUri,
            address = address,
            addressName = addressInfo?.name,
            addressUri = addressInfo?.logoUri
    )
    return item
}

fun Context.getArrayItem(chain: Chain, name: String, value: List<Any>, paramType: ParamType, paramTypeValue: String, addressInfoIndex: Map<String, AddressInfo>?): LabeledArrayItem {
    val item = LabeledArrayItem(this)
    val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    layoutParams.setMargins(0, 0, 0, 0)
    item.layoutParams = layoutParams
    item.label = name
    item.showArray(chain, value, paramType, paramTypeValue, addressInfoIndex)
    return item
}

fun Context.getDataItem(name: String, value: String): TxDataView {
    val item = TxDataView(this)
    val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    layoutParams.setMargins(dpToPx(16), dpToPx(16), dpToPx(16), 0)
    item.layoutParams = layoutParams
    val size = value.removeHexPrefix().length / 2
    item.setData(value, size, name)
    return item
}

fun Context.getLabeledAddressItem(chain: Chain, name: String, value: Solidity.Address, addressInfo: AddressInfo?): View {
    var item: View
    if (addressInfo == null) {
        item = LabeledAddressItem(this)
        item.label = name
        item.setAddress(chain, value)
    } else {
        item = LabeledNamedAddressItem(this)
        item.label = name
        item.setAddress(chain, value)
        // only old imported owner keys could have empty names
        item.name = if (addressInfo.name.isNullOrBlank()) {
            getString(R.string.settings_app_imported_owner_key_default_name, value.shortChecksumString())
        } else {
            addressInfo.name
        }
        addressInfo.logoUri?.let { item.loadKnownAddressLogo(it, value) }
    }
    val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    layoutParams.setMargins(0, 0, 0, 0)
    item.layoutParams = layoutParams
    return item
}

fun Context.getLabeledValueItem(name: String, value: String): LabeledValueItem {
    val item = LabeledValueItem(this)
    val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    layoutParams.setMargins(0, 0, 0, 0)
    item.layoutParams = layoutParams
    item.label = name
    item.value = value
    return item
}

fun Context.getDivider(): View {
    val item = View(this)
    val height = resources.getDimension(R.dimen.item_separator_height).toInt()
    val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
    layoutParams.setMargins(0, dpToPx(16), 0, 0)
    item.layoutParams = layoutParams
    item.setBackgroundColor(ContextCompat.getColor(this, R.color.separator))
    return item
}

fun Context.getDividerBig(): View {
    val item = View(this)
    val height = resources.getDimension(R.dimen.default_large_margin).toInt()
    val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
    layoutParams.setMargins(0, dpToPx(16), 0, 0)
    item.layoutParams = layoutParams
    item.setBackgroundColor(ContextCompat.getColor(this, R.color.separator))
    return item
}
