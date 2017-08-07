package pm.gnosis.android.app.wallet.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import dagger.Component
import org.ethereum.geth.KeyStore
import pm.gnosis.android.app.wallet.data.contracts.GnosisMultisig
import pm.gnosis.android.app.wallet.data.geth.GethAccountManager
import pm.gnosis.android.app.wallet.data.geth.GethNodeManager
import pm.gnosis.android.app.wallet.data.geth.GethRepository
import pm.gnosis.android.app.wallet.data.remote.InfuraRepository
import pm.gnosis.android.app.wallet.di.ApplicationContext
import pm.gnosis.android.app.wallet.di.module.ApplicationModule
import pm.gnosis.android.app.wallet.di.module.EthereumModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, EthereumModule::class))
interface ApplicationComponent {
    fun application(): Application
    @ApplicationContext fun context(): Context
    fun keyStore(): KeyStore
    fun moshi(): Moshi
    fun sharedPreferences(): SharedPreferences
    fun gethRepo(): GethRepository
    fun infuraRepository(): InfuraRepository

    fun ethereumConnector(): GethNodeManager
    fun gnosisMultiSig(): GnosisMultisig
}
