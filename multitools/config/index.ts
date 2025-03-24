import {z} from "zod"

const envVars = z.object({
    BASE_URL: z.string()
})

envVars.parse(process.env);

declare global{
    namespace NodeJS{
        interface ProcessEnvv extends z.infer<typeof envVars>{}
    }
}