package io.gnosis.safe.ui.settings.owner.list

import io.gnosis.data.models.Owner
import io.gnosis.safe.R
import pm.gnosis.model.Solidity

data class OwnerViewData(
    val address: Solidity.Address,
    val name: String?,
    val type: Owner.Type
)

fun Owner.Type.stringRes(): Int =
    when(value) {
        0 -> R.string.signing_owner_details_owner_type_imported
        1 -> R.string.signing_owner_details_owner_type_generated
        2 -> R.string.signing_owner_details_owner_type_ledger
        else -> R.string.signing_owner_details_owner_type_imported
    }
