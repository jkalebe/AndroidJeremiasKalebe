package br.com.paygo.smart.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jkalebe.androidjeremiaskalebe.domain.models.database.ClientEntity
import com.jkalebe.androidjeremiaskalebe.domain.models.database.PedidoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirPedido(pedido: ClientEntity)

    @Query("SELECT * FROM Pedidos")
    fun obterTodosPedidos(): Flow<List<PedidoEntity>>

    @Query("SELECT * FROM Pedidos WHERE id = :pedidoId")
    fun obterPedidoPorId(pedidoId: Int): Flow<PedidoEntity?>

    @Update
    suspend fun atualizarPedido(pedido: PedidoEntity)
}